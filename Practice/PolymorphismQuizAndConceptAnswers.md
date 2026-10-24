# Week 08 Practice — Quiz and Concept Answers

Answers to the non-coding **quiz questions** and **concept questions** from the Week 8 practice set.

## Quiz answers

1. **C — Polymorphism.** Call the common `render()` method and let runtime dispatch select the implementation.
2. **A, B, C.** Car is a Vehicle; Rectangle is a Shape; and a DatabaseConnection can be modeled as a NetworkResource. A HelperUtility containing a Calculator is a has-a/composition relationship, not an is-a relationship.
3. **C — Runtime method dispatch.** The actual object's overridden method is selected at runtime.
4. **C — Inheritance-based polymorphism.** A base-type reference can call the subclass's area implementation.
5. **A, C, D.** The Book can extend inherited loan-period behavior; LibraryItem provides shared behavior; and a DVD can be used through a LibraryItem reference. DVD's fixed period is specialized rather than shared behavior.
6. **A, C.** An Animal reference to a Dog dispatches to Dog's override, and Dog's `makeSound()` is overridden behavior.
7. **C.** A new WalletPayment can be introduced without changing the processor's common iteration logic, provided it conforms to the common base type.
8. **A.** Inheriting unrelated types creates tight coupling and an incorrect is-a relationship, making design rigid.
9. **A, C.** A generic sender can process any Notification subtype, and new channels can be added without changing sender logic.
10. **A, B, C.** Polymorphic collections support uniform processing, simplify iteration, and reduce repeated type checks/casts.

## Concept answers

1. **Reuse and specialization:** Inheritance lets a subtype reuse fields and methods from a base class and override only the behavior that differs. For example, `Employee` can hold a name while `FullTimeEmployee` and `Intern` calculate bonuses differently.
2. **Is-a relationship:** A subtype should genuinely be a kind of its base type, such as `Car` is a `Vehicle`. Correct modeling keeps substitutability meaningful and avoids designs that merely reuse code without matching the real relationship.
3. **Method overriding:** A subclass supplies its own implementation of an inherited instance method with the same signature. For example, `FullTimeEmployee.calculateBonus()` may return 10% of salary, while `Intern.calculateBonus()` returns a fixed amount.
4. **Runtime polymorphism:** A base reference can hold a derived object. When an overridden instance method is called, Java chooses the implementation based on the object's actual runtime class.
5. **Polymorphic collections:** An array or list of `Notification` references can hold EmailNotification, SMSNotification, and PushNotification objects. A loop can call `send()` uniformly while each object uses its own behavior.
6. **Polymorphism vs. conditionals:** Repeated type checks put type-specific rules in one growing conditional. Overriding localizes each rule in its class and lets common code process all subtypes uniformly.
7. **Extensibility:** A new `WalletPayment` subtype can implement `calculateFee()` and join the existing list of `Payment` references. The processor's loop need not change.
8. **Inherited vs. overridden behavior:** A subtype inherits a method when the base implementation is suitable. It overrides the method when the subtype needs behavior appropriate to itself, such as a TruckRental calculating distance charges differently from a CarRental.
9. **Inheritance for superficial reuse:** Unrelated classes may not honor the base type's contract. This creates unnecessary coupling, surprises, and a rigid class hierarchy; composition is often a better fit for has-a relationships.
10. **Vehicle rentals:** A common `Rental` base can store customer, duration, and common booking behavior. `CarRental` and `TruckRental` can override pricing or vehicle-specific rules. Shared processing can call a common `calculateCost()` method without checking every rental type.

These are concise study answers; expand the examples in your own words when preparing for a viva or written response.
