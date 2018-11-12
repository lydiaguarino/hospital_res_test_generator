# Welcome to the Hospital Resident Matching with Couples test file generator!

This generator takes some configuration arguments and generates two output files:
a CSV representing hospitals and a CSV representing residents.

### To use:
run script with the following args:

`<String filenamePrefix>  <int hospitalCount> <int locationCount> <int residentCount> <int coupleCount>`

ex: `test0 20 5 35 8`

|                  |                                                                                                                |
|------------------|----------------------------------------------------------------------------------------------------------------|
|`filenamePrefix`  | the file name identifier for the configuration run                                                             |
|`hospitalCount`   |  the size of the pool of hospitals in the matching                                                             |
|`locationCount`   |  the number of unique locations that should be used for the hospital pool                                      |
|`residentCount`   | the total size of the pool of residents (including coupled and non-coupled) in the matching                    |
|`coupleCount`     | the number of couples you would like to use in the matching. Note that this number must be < residentCount / 2 |


## Example output:

test0_hospitals.csv

| `hospitalId` | `locationId` | `capacity` | `preferences` |

```
H0,2,5,R21 R17 R26 R3 R31 R18 R1 R30 R33 R7 R8 R20 R25 R28 R24
H1,1,5,R1 R30 R15 R23 R18 R29 R6 R8 R31 R19 R16 R33 R14 R9 R17
H2,0,4,R20 R26 R4 R2 R9 R17 R10 R16 R31 R28 R5 R34 R32 R22 R30
H3,2,1,R4 R25 R34 R18 R27 R28 R20 R19 R11 R2 R1 R32 R33 R26 R0
H4,1,3,R31 R14 R27 R16 R17 R25 R11 R4 R12 R0 R34 R33 R2 R19 R13
H5,0,4,R21 R5 R2 R14 R31 R12 R13 R17 R8 R19 R34 R10 R4 R16 R24
H6,2,3,R18 R7 R31 R29 R1 R3 R10 R22 R26 R12 R11 R30 R15 R24 R27
H7,2,1,R12 R19 R1 R20 R7 R30 R23 R32 R6 R15 R10 R11 R18 R16 R29
H8,1,2,R11 R25 R4 R23 R30 R32 R26 R8 R12 R0 R34 R27 R13 R24 R9
H9,4,4,R17 R11 R8 R24 R1 R10 R4 R20 R5 R7 R0 R31 R32 R9 R23
H10,2,4,R17 R26 R14 R30 R12 R33 R18 R22 R32 R10 R34 R25 R8 R7 R19
H11,0,3,R1 R14 R8 R16 R25 R3 R24 R33 R0 R4 R23 R11 R7 R12 R21
H12,3,1,R14 R25 R30 R28 R8 R20 R10 R6 R29 R11 R13 R1 R9 R34 R26
H13,0,4,R21 R18 R14 R3 R9 R32 R10 R8 R13 R11 R0 R23 R5 R4 R2
H14,2,2,R17 R29 R28 R3 R27 R20 R16 R15 R10 R9 R0 R26 R11 R32 R6
H15,0,3,R22 R13 R6 R19 R15 R12 R23 R0 R10 R16 R20 R4 R26 R29 R24
H16,0,3,R20 R25 R34 R6 R7 R27 R17 R12 R23 R15 R22 R16 R13 R18 R8
H17,0,3,R32 R19 R6 R21 R33 R9 R27 R12 R20 R17 R31 R2 R16 R25 R1
H18,0,1,R9 R1 R30 R19 R14 R7 R5 R26 R13 R21 R33 R25 R8 R4 R27
H19,4,4,R25 R14 R20 R34 R32 R19 R30 R9 R31 R24 R8 R28 R11 R12 R22
```

test0_residents.csv

| `residentId` | `preferences` | `partner` |

```
R0,H13 H2 H16 H14 H9 H11 H3 H8 H4 H5 H17 H15,R19
R1,H3 H18 H0 H1 H7 H6 H16 H17 H11 H14 H4 H9 H5 H12 H8,R27
R2,H19 H12 H17 H2 H6 H3 H8 H18 H7 H9 H13 H14 H5 H11 H4,R28
R3,H4 H2 H6 H0 H19 H12 H14 H7 H13 H11 H9 H3 H15 H5 H16,none
R4,H4 H5 H18 H17 H14 H15 H16 H19 H11 H13 H3 H8 H2 H7 H9,none
R5,H5 H18 H13 H8 H15 H19 H7 H17 H9 H2 H1,R15
R6,H1 H5 H15 H7 H16 H13 H9 H12 H0 H10 H14 H18 H11 H6 H17,R23
R7,H0 H11 H9 H13 H12 H10 H5 H18 H6 H14 H19 H2 H7 H17 H16,none
R8,H0 H13 H19 H18 H14 H10 H1 H5 H15 H11 H7 H8 H9 H16 H12,R14
R9,H14 H5 H1 H19 H8 H2 H13 H7 H16 H4 H9 H18 H17 H12 H6,none
R10,H14 H2 H1 H9 H13 H5 H17 H8 H15 H10 H4 H12 H6 H7 H3,none
R11,H16 H13 H4 H12 H15 H5 H8 H0 H7 H11 H3 H9 H6 H19 H14,R13
R12,H6 H19 H5 H16 H10 H1 H11 H2 H4 H3 H15 H17 H8 H7 H9,R22
R13,H8 H7 H12 H4 H11 H5 H16 H18 H15 H1 H13,R11
R14,H0 H14 H12 H11 H4 H15 H5 H16 H10 H19 H13 H1 H18,R8
R15,H8 H13 H15 H18 H1 H7 H9 H2 H6 H5 H14 H17 H4 H16 H19,R5
R16,H4 H5 H15 H11 H2 H8 H7 H14 H3 H16 H17 H18 H19 H9 H1,none
R17,H14 H2 H10 H11 H9 H0 H12 H15 H1 H8 H19 H17 H4 H16 H5,none
R18,H10 H9 H6 H14 H1 H7 H2 H11 H5 H12 H0 H16 H13 H4 H3,none
R19,H5 H9 H15 H14 H16 H3 H17 H18 H7 H19 H10 H1 H0 H4 H2,R0
R20,H12 H0 H14 H18 H5 H16 H9 H4 H3 H15 H17 H19 H6 H2 H7,none
R21,H18 H0 H10 H4 H16 H9 H6 H13 H17 H2 H11 H7 H5 H1 H3,none
R22,H2 H15 H18 H19 H13 H11 H6 H5 H10 H1 H16,R12
R23,H4 H1 H11 H3 H9 H8 H5 H16 H13 H15 H7,R6
R24,H4 H12 H15 H16 H19 H0 H18 H14 H5 H8 H9 H3 H2 H11 H6,none
R25,H18 H6 H16 H0 H13 H12 H2 H17 H15 H3 H11 H19 H8 H4 H10,none
R26,H16 H12 H3 H6 H4 H7 H8 H15 H10 H14 H5 H0 H19 H2 H18,none
R27,H6 H4 H8 H7 H14 H3 H0 H13 H1 H18 H16 H17,R1
R28,H8 H2 H17 H14 H1 H3 H19 H0 H6 H12,R2
R29,H0 H1 H4 H15 H11 H7 H12 H10 H16 H17 H19 H6 H2 H9 H14,none
R30,H15 H9 H6 H0 H18 H8 H13 H2 H3 H10 H19 H5 H1 H12 H7,none
R31,H16 H4 H0 H2 H12 H14 H19 H5 H10 H1 H8 H9 H15 H17 H6,none
R32,H2 H3 H10 H6 H12 H17 H9 H8 H14 H18 H7 H1 H19 H16 H13,none
R33,H11 H17 H14 H1 H6 H9 H15 H0 H5 H3 H7 H16 H4 H10 H18,none
R34,H7 H5 H4 H19 H2 H10 H11 H3 H12 H17 H14 H15 H16 H8 H13,none
```