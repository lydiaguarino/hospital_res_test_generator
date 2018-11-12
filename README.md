# Welcome to the Hospital Resident Matching with Couples test file generator!

This generator takes some configuration arguments and generates two output files:
a CSV representing hospitals and a CSV representing residents.

### To use:
run script with the following args:
`<String filenamePrefix>  <int hospitalCount> <int locationCount> <int residentCount> <int coupleCount>`
ex: `test0 20 5 35 8`

|----|----|
|`filenamePrefix` | the file name identifier for the configuration run |
|`hospitalCount` |  the size of the pool of hospitals in the matching |
|`locationCount` |  the number of unique locations that should be used for the hospital pool |
|`residentCount` | the total size of the pool of residents (including coupled and non-coupled) in the matching |
|`coupleCount` | the number of couples you would like to use in the matching. Note that this number must be < residentCount / 2 |
