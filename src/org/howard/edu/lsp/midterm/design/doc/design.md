# Redesigned Order Processing System — CRC Cards

Class: Order
Responsibilities:
- Store order data (customerName, email, item, price)
- Provide order details to requesting classes
Collaborators:
- None

Class: PricingService
Responsibilities:
- Calculate tax on order price
- Apply discount based on order total
- Compute and return final price
Collaborators:
- Order

Class: ReceiptPrinter
Responsibilities:
- Print order summary to screen
- Display final price from PricingService
Collaborators:
- Order
- PricingService

Class: OrderRepository
Responsibilities:
- Save completed order to file
Collaborators:
- Order

Class: EmailService
Responsibilities:
- Send confirmation email to customer
Collaborators:
- Order

Class: ActivityLogger
Responsibilities:
- Log order activity with timestamp
Collaborators:
- Order