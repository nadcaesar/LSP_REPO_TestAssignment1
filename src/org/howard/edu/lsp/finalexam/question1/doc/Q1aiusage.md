# AI Usage Report — Final Exam Question 1

## AI Tools Used

Claude by Anthropic (claude.ai)

## Prompts Used

1. What are the shared resources in the RequestManager class?
2. Why is addRequest() unsafe for concurrent use?
3. Which of the three fixes correctly solves the concurrency problem and why?
4. Should getNextId() be public according to Riel's heuristics?
5. How does ReentrantLock work as an alternative to synchronized?

## How AI Helped

Claude explained race conditions and thread safety concepts from scratch,
guiding understanding of why addRequest() is unsafe by walking through
the interleaving of steps between threads. Claude also explained
ReentrantLock and why the finally block is critical for guaranteed unlock.

## Reflection

I learned that concurrency bugs are not about CPU usage but about the
ordering and interleaving of steps across threads. The distinction
between protecting a single operation versus an entire method was key
to understanding why Fix B was the only correct solution.

---

[PFinal_Exam_--_Spring_2026.docx
docx

6:22 PM
Claude responded: Okay I've read the full final exam.

Read the final exam document
Read the final exam document
Okay I've read the full final exam. Same question as always before we start:

Also worth noting — Q4 explicitly says "Do NOT use AI tools to generate your response" regardless of what you answer above. That one you'll need to write yourself. Your call on the rest!

You said: Q: Does this final exam allow AI assistance?
Q: Does this final exam allow AI assistance?
A: Yes — AI is explicitly allowed like the midterm

6:24 PM
Claude responded: Great.
Great. Let's work through it the same way we did the midterm — I explain concepts, you reason through answers, we build solutions together.

Final Exam Overview
Question	Topic	Points
Q1	Concurrency / Thread Safety	35 pts
Q2	Template Method Design Pattern	30 pts
Q3	JUnit 5 Tests for GradeCalculator	20 pts
Q4	AI Reflection — YOU write this alone	5 pts
Q5	Arthur Riel's OO Heuristics	10 pts
Total	100 pts
⚠️ One Important Note on Q4
The exam explicitly says:

"This question must be answered independently. Do NOT use AI tools to generate your response."

I will help you with Q1, Q2, Q3, and Q5. For Q4 I can remind you what we worked on together so you have material to draw from — but the actual writing must be yours.

Let's Start With Q1 — Concurrency
This is worth the most points so let's nail it.

Before I explain anything — read the RequestManager code and answer these in plain English:

Part 1 Questions:

What are the TWO shared resources that multiple threads could access at the same time?
What do you think could go wrong if two threads call addRequest() at exactly the same time?
Why specifically is addRequest() unsafe — what's the sequence of steps that could cause a problem?
Take your best guess — you've seen enough Java now to reason through this!

You said: The sudent Id and the student name
The sudent Id and the student name
It might be stuck forever or the second request might not be see
It is unsafe becuase it can take up a massive amount of cpu usage
6:33 PM
Claude responded: Good attempt — you're thinking in the right direction but not quite there yet.
Good attempt — you're thinking in the right direction but not quite there yet. Let me guide you to the precise answers.

Q1 — Let's Sharpen Your Thinking
Shared Resource #1 — close but not quite nextId is correct — that's a shared resource. But studentName is just a parameter passed into the method — it's not shared between threads. Think about what the class stores that multiple threads could both touch at the same time.

The class has two fields: nextId and requests. Both are shared resources.

Shared Resource #2 The requests ArrayList — multiple threads could try to add to it simultaneously.

Concurrency Problem — you said "stuck forever" "Stuck forever" is actually a real concurrency problem called a deadlock — but that's not what's happening here. The problem here is called a race condition.

Think about this scenario:

Thread A calls getNextId() and reads nextId = 1
Before Thread A can increment it, Thread B also calls getNextId() and reads nextId = 1
Now BOTH threads got ID 1 — duplicate IDs!
That's a race condition — two threads racing to read/write the same data.

Why addRequest() is unsafe — not CPU usage CPU usage isn't the issue. The problem is the sequence of steps inside addRequest():

java
public void addRequest(String studentName) {
    int id = getNextId();        // Step 1 — read and increment nextId
    String request = "Request-" + id + " from " + studentName;  // Step 2
    requests.add(request);       // Step 3 — add to ArrayList
}
If two threads execute these steps simultaneously they can interleave — Thread A does Step 1, Thread B does Step 1, now both have the same ID. The three steps are not atomic — they're not guaranteed to complete without interruption.

Now Part 2 — Evaluate the Three Fixes
Read each fix carefully and tell me:

Is it correct or not correct?
Why?
java
// Fix A
public synchronized int getNextId() { ... }

// Fix B
public synchronized void addRequest(String studentName) { ... }

// Fix C
public synchronized List<String> getRequests() { ... }
Think about what synchronized does — it means only ONE thread can execute that method at a time. With that in mind:

Fix A — only synchronizes getNextId(). Does that protect ALL the steps in addRequest()?
Fix B — synchronizes the entire addRequest() method. Does that cover everything?
Fix C — synchronizes getRequests(). Does reading the list cause the race condition?
What do you think for each one — correct or not correct?

You said: Fix B because we need to sync everything at once
Fix B  because we need to sync everything at once

6:42 PM
Claude responded: Exactly right on Fix B — but you need to evaluate all three for full points.
Exactly right on Fix B — but you need to evaluate all three for full points. Let me help you think through A and C:

Fix A — synchronized getNextId() only
Think about this scenario with Fix A applied:

Thread A enters addRequest() and calls getNextId() — gets ID 1, lock releases
Thread B immediately enters addRequest() and calls getNextId() — gets ID 2, lock releases
Now Thread A and Thread B are BOTH inside addRequest() at the same time
They both call requests.add() simultaneously — ArrayList is not thread-safe!
So Fix A protects nextId from duplicates but does NOT protect the ArrayList. Two threads can still corrupt requests by adding at the same time.

Fix A = ❌ Not correct — only protects nextId, not the ArrayList

Fix C — synchronized getRequests()
Ask yourself — does getRequests() cause the race condition? The race condition happens when two threads write at the same time inside addRequest(). getRequests() just reads the list — it doesn't modify anything.

Synchronizing the read doesn't fix the problem of two threads writing simultaneously.

Fix C = ❌ Not correct — synchronizes reading not writing, doesn't fix the race condition

Summary for Part 2
Fix	Correct?	Why
Fix A	❌	Only protects nextId, ArrayList still unsafe
Fix B	✅	Locks entire addRequest() — only one thread runs all three steps at a time
Fix C	❌	Synchronizes reading not writing — doesn't fix the race condition
Now Part 3 — Riel's Heuristic
The question asks:

Should getNextId() be public?

Think back to what we discussed about Arthur Riel's heuristics. One of them says:

"Minimize the public interface of a class"

getNextId() is only ever called from inside addRequest() — it's an internal helper method. No outside class needs to call it directly.

Based on that — should it be public or private, and why?

You said: It should not be public because the class should hide implementation details from the public interface.
It should not be public because the class should hide implementation details from the public interface. Making it private keeps to the idea that the public interface should only reflect important operations

6:47 PM
Claude responded: Perfect answer — that's exactly what Riel's heuristic says.
Perfect answer — that's exactly what Riel's heuristic says. Clean, precise, well-reasoned. ✅

Now Part 4 — Alternative Synchronization
The question asks about an alternative to synchronized that was discussed in lecture.

The answer is ReentrantLock from java.util.concurrent.locks.

Here's the concept:

Instead of marking a method with synchronized, you manually lock before the critical section and unlock after it — giving you more control.

java
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
Three things to notice:

1. lock.lock() — acquires the lock, only one thread gets through at a time

2. try/finally — the finally block always runs even if an exception occurs. This guarantees the lock is always released. Without this, a crash inside the method would leave the lock permanently locked — freezing all other threads forever.

3. lock.unlock() — releases the lock so the next thread can enter

Why Use ReentrantLock Over synchronized?
Feature	synchronized	ReentrantLock
Automatic release	✅	❌ (must unlock manually)
Try to acquire without blocking	❌	✅ (tryLock())
Fairness control	❌	✅
More explicit/readable	❌	✅
For this exam the key point is: ReentrantLock gives more fine-grained control over locking than synchronized.

Q1 is Complete — Let's Build the answers.md
Here's everything together. Confirm you understand it and I'll generate the file:

Part 1:
Shared Resource #1: nextId (integer counter shared across threads)
Shared Resource #2: requests (ArrayList shared across threads)

Concurrency Problem: Race condition — two threads can read/write
shared resources simultaneously producing incorrect results

Why addRequest() is unsafe: The three steps (getNextId, build string,
add to list) are not atomic. Two threads can interleave between steps,
causing duplicate IDs or corrupting the ArrayList.

Part 2:
Fix A: Not correct — only synchronizes getNextId(), leaving the
ArrayList unprotected. Two threads can still corrupt requests.

Fix B: Correct — synchronizes the entire addRequest() method,
ensuring only one thread executes all three steps at a time.

Fix C: Not correct — synchronizes reading not writing. The race
condition happens during addRequest() writes, not getRequests() reads.

Part 3:
getNextId() should NOT be public. Per Riel's heuristic, the public
interface should only expose meaningful operations. getNextId() is an
internal helper only used by addRequest() — making it private hides
implementation details and keeps the interface clean.

Part 4:
ReentrantLock from java.util.concurrent.locks — manually lock before
the critical section and unlock in a finally block to guarantee release.
Does that all make sense before I generate the file? Any part you want me to explain more?

You said: Yes
Yes]
