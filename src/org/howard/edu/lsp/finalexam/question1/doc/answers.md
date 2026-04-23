# Final Exam — Question 1 Answers

## Part 1: Shared Resources and Risk

**Shared Resource #1:**
`nextId` — an integer counter shared across all threads. Multiple threads
can read and increment it simultaneously, causing duplicate IDs.

**Shared Resource #2:**
`requests` — an ArrayList shared across all threads. Multiple threads
can attempt to add to it simultaneously, corrupting the list.

**Concurrency Problem:**
Race condition — two or more threads access and modify shared resources
simultaneously without coordination, producing incorrect or inconsistent
results. For example, two threads can both read `nextId = 1` before
either increments it, resulting in duplicate request IDs.

**Why addRequest() is unsafe:**
The three steps inside addRequest() are not atomic:

1. getNextId() — reads and increments nextId
2. Builds the request string
3. Adds to the requests ArrayList

Two threads can interleave between these steps. Thread A can read
nextId = 1, then Thread B reads nextId = 1 before Thread A increments
it — both get the same ID. Similarly, both threads can call
requests.add() simultaneously, corrupting the ArrayList since ArrayList
is not thread-safe.

---

## Part 2: Evaluate Fixes

**Fix A: `public synchronized int getNextId()`**
NOT correct. Synchronizing only getNextId() protects nextId from
duplicate IDs but does NOT protect the requests ArrayList. Two threads
can still be inside addRequest() at the same time and call
requests.add() simultaneously, corrupting the list. The race condition
is only partially addressed.

**Fix B: `public synchronized void addRequest(String studentName)`**
CORRECT. Synchronizing the entire addRequest() method ensures only one
thread can execute all three steps at a time. This protects both nextId
(via the call to getNextId()) and the requests ArrayList from concurrent
access. The lock is held for the full duration of the method.

**Fix C: `public synchronized List<String> getRequests()`**
NOT correct. Synchronizing getRequests() only protects the read
operation. The race condition occurs during writes inside addRequest(),
not during reads. This fix does nothing to prevent two threads from
writing simultaneously and does not address the concurrency problem.

---

## Part 3: Object-Oriented Design

**Should getNextId() be public?**
No. Per Arthur Riel's heuristic, a class should minimize its public
interface and only expose operations that are meaningful to outside
callers. getNextId() is an internal helper method used exclusively by
addRequest() — no outside class needs to call it directly. Making it
private hides the implementation detail, reduces the public interface,
and prevents external code from accidentally misusing or bypassing the
ID generation logic.

---

## Part 4: Alternative Synchronization Approach

**Description:**
ReentrantLock from java.util.concurrent.locks is an alternative to the
synchronized keyword. Instead of marking a method as synchronized, you
manually acquire the lock before the critical section and release it
afterward. The lock must always be released in a finally block to
guarantee it is unlocked even if an exception occurs inside the critical
section. This gives more fine-grained control over locking compared to
synchronized, including the ability to try to acquire a lock without
blocking using tryLock().

**Code Snippet:**

```java
import java.util.concurrent.locks.ReentrantLock;

private final ReentrantLock lock = new ReentrantLock();

public void addRequest(String studentName) {
    lock.lock();
    try {
        int id = getNextId();
        String request = "Request-" + id + " from " + studentName;
        requests.add(request);
    } finally {
        lock.unlock();
    }
}
```
