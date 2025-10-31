# W1D3 – Sorted Square Matrix Search (Java)

This mini project includes:
- `searchSS` (iterative “staircase” search): **O(n)** time, **O(1)** space.
- `DACsearchSS` (divide-and-conquer search): **O(n^{log2 3}) ≈ O(n^{1.585})** time, **O(log n)** space.
- Simple matrix builders that guarantee rows & columns are increasing (M1, M2, M3).

## Files
- `MatrixBuilders.java` – generators for sample sorted matrices and a `printMatrix` helper.
- `SearchAlgorithms.java` – implementations of `searchSS` and `DACsearchSS`.
- `Demo.java` – quick demo to run and compare results.

## Run
Compile and run with any JDK (e.g., OpenJDK 24):

```bash
javac *.java
java Demo
```

## Concepts linked to your course
- Divide & Conquer (non-overlapping subproblems), Master Theorem.
- Asymptotic analysis and algorithm choice by data structure properties.
- Loop invariants in iterative search; recursion depth in DAC.
