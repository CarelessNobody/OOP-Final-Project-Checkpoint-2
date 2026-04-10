# Argument System

Handles parsing a single String input value into typed data.

## Development Notes
I used a strategy type method to make the arg parser since I think that would 
make it most flexible, but it does also lose out on extra support offered by having 
specific implementations for each primitive type.

Added built-in parse method for some primitives, will support better handling in the future. 
Also makes it more user-friendly for most people.

## PoC Design Analysis

### Individual Review (Argument Lead)
Ruihan - I think making ParseArgType a utility class and all its method static is good since 
it will not be storing any state. Another convenient feature is having the methods take in a 
custom parsing function with some built-in double, int, and boolean parsing. However, I think 
I could handle exceptions better (not just catching them all and more info on specific).

### Individual Review (Command Lead)
Ephraim - I think the use of a utility class along with static methods was a good choice for the 
argument system's implementation, as it keeps the system simple and easy while also supporting 
various custom parsing through the function parameters. Having built in parsing methods was also a 
nice touch and improves usability. The primary critique I would have was already mentioned by Ruihan, 
which is providing more specific exception messages. Handling these errors in a more specific manner
would go a long way in users debugging issues.

### Team Review
