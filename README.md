# STEP Week 08: Inheritance and Polymorphism

My solutions to the Week 8 practice problems and coding assignment of the **STEP Java Programming** track.

## Topics covered

- Inheritance and abstract base classes
- Method overriding
- Runtime polymorphism
- Type-specific pricing rules
- Date calculations with `LocalDate`

## Practice

| Program | What it does | Input |
|---------|--------------|-------|
| [`DeliveryFeeCalculator`](Practice/DeliveryFeeCalculator.java) | Calculates fees for standard, express and international deliveries | Interactive |
| [`ExaminationQuestionGrader`](Practice/ExaminationQuestionGrader.java) | Grades multiple-choice, true/false and essay questions | Interactive |
| [`LibraryItemDueDateCalculator`](Practice/LibraryItemDueDateCalculator.java) | Works out due dates for books, DVDs and magazines with `LocalDate` | Interactive |
| [`PaymentSystemFeeCalculation`](Practice/PaymentSystemFeeCalculation.java) | Calculates processing fees for card, wallet and bank-transfer payments | Interactive |
| [`PublicTransportFareCalculator`](Practice/PublicTransportFareCalculator.java) | Calculates bus, train and metro fares, including a metro peak factor | Interactive |

Also included: [`PolymorphismQuizAndConceptAnswers.md`](Practice/PolymorphismQuizAndConceptAnswers.md) (answers to the quiz and concept questions).

## Assignment

| Program | What it does | Input |
|---------|--------------|-------|
| [`CampusParkingChargeCalculator`](Assignment/CampusParkingChargeCalculator.java) | Calculates parking charges for bikes, cars and trucks | Interactive |
| [`CanteenBillingCounter`](Assignment/CanteenBillingCounter.java) | Bills students, staff and guests with different discounts and charges | Interactive |
| [`FestivalBonusCalculator`](Assignment/FestivalBonusCalculator.java) | Calculates festival bonuses for full-time, part-time and intern employees | Interactive |
| [`HostelElectricityBill`](Assignment/HostelElectricityBill.java) | Calculates electricity bills for single, shared and AC rooms | Interactive |
| [`StreamingPlanRenewalReminder`](Assignment/StreamingPlanRenewalReminder.java) | Works out renewal dates for basic, standard and premium plans | Interactive |

## Project structure

```
.
├── Practice/
│   ├── DeliveryFeeCalculator.java
│   ├── ExaminationQuestionGrader.java
│   ├── LibraryItemDueDateCalculator.java
│   ├── PaymentSystemFeeCalculation.java
│   ├── PublicTransportFareCalculator.java
│   ├── PolymorphismQuizAndConceptAnswers.md
│   └── README.md
├── Assignment/
│   ├── CampusParkingChargeCalculator.java
│   ├── CanteenBillingCounter.java
│   ├── FestivalBonusCalculator.java
│   ├── HostelElectricityBill.java
│   ├── StreamingPlanRenewalReminder.java
│   └── README.md
└── README.md
```

## How to run

You need JDK 17 or later.

```bash
cd Practice      # or: cd Assignment
javac FileName.java
java FileName
```

Replace `FileName` with the program name. Programs marked **Interactive** read input from the terminal; the rest run a built-in example straight away. Each file is self-contained, so you can also open it in VS Code or IntelliJ and press **Run**.

## All STEP weeks

| Week | Topic |
|------|-------|
| [Week 1](https://github.com/adityashukla44/STEP-Week-01) | Java Basics: Strings, Loops and Arrays |
| [Week 2](https://github.com/adityashukla44/STEP-Week-02) | String Processing and Validation |
| [Week 3](https://github.com/adityashukla44/STEP-Week-03) | Classes, Objects and Constructors |
| [Week 4](https://github.com/adityashukla44/STEP-Week-04) | Array Algorithms |
| [Week 5](https://github.com/adityashukla44/STEP-Week-05) | Arrays and Methods |
| [Week 6](https://github.com/adityashukla44/STEP-Week-06) | Object-Oriented Programming Revision |
| [Week 7](https://github.com/adityashukla44/STEP-Week-07) | Encapsulation |
| **Week 8** | **Inheritance and Polymorphism** (this repo) |
| [Week 9](https://github.com/adityashukla44/STEP-Week-09) | Abstraction and Interfaces |
| [Week 10](https://github.com/adityashukla44/STEP-Week-10) | Coding Practice Set |

## Author

**Aditya Shukla** · [@adityashukla44](https://github.com/adityashukla44)
