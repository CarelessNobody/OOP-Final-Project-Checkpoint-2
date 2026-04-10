# Command System

Handles creation of creation command structures and multi-argument parsing.

## Development Notes

I initially focused on getting positional and named argument parsing functional, then
I organized all the logic into three different classes: CommSpec, CommParsers, and
ParsedComm.

A custom parser interface was used so that the Command System could directly use the already
implemented Argument System's parsing methods, including methods that throw exceptions.

Overall, quite a simple implementation that meets requirements, though it is limited in some
aspects. May be cleaner ways to make implementation more fluid.

## PoC Design Analysis

### Individual Review (Command Lead)
Ephraim - A part that I believe I implemented well was the separation of command definition, 
parsing, and results into 3 different classes, which kept things more modular. Additionally, 
the use of a custom parser interface made it very easy to integrate well with the Argument System. 
One potential weakness is that the current system relies on strict argument counts, lacking 
flexibility for things like optional arguments.

### Individual Review (Argument Lead)
Ruihan - I think the modularity in the command parser is nice, but I think having a separate
parsedComm is a bit excessive instead of having the class store the map. I think a 
builder design pattern would work great here. The only real issue here is I think the 
named and positional arguments are very separated and a bit awkward.

### Team Review
The overall design is modular, maintaining a clear separation of responsibilities between the argument
parsing and command handling. This makes the system easy to understand and extend when needed. A major 
strength is the flexibility given by the custom parser interface, which allows for custom types and
validations without the need to modify the Command System. Though, the API could be more unified, such as
through the handling of optional arguments. Improvement towards how positional and named arguments interact
with each other may also be considered.