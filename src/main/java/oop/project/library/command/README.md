# Argument System

Handles creation of creation command structures and multi-argument parsing.

## Development Notes

TODO: Keep a running log of design decisions, tradeoffs, and other observations.

## PoC Design Analysis

### Individual Review (Command Lead)

### Individual Review (Argument Lead)
I think the modularity in the command parser is nice, but I think having a separate
parsedComm is a bit excessive instead of having the class store the map. I think a 
builder design pattern would work great here. The only real issue here is I think the 
named and positional arguments are very separated and a bit awkward.

### Team Review
