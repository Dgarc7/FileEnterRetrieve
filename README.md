# FileEnterRetrieve

There are a few things that need to be optimized as 
well as a few exceptions that could be caught in
order to make this a more user friendly class. 
On top of that, a Pattern funcionality will later be 
placed into this class so that regex can also be used when
searching through files.

***NOTE: Anything that has been commented out was left there
as a guide for anyone who may decided to take the route
of leaving any writers/readers inside of the actual class.
Although it is possible, I opted to only open any I/O when
necessary and closed them after all operations that they
were needed for were completed as this is safer for 
preventing memory leaks.

***NOTE: You will get 9 warnings when compiling this program.
THIS WAS COMPLETELY INTENTIONAL. Due to the nature of how 
lists work between methods, the compiler will complain because
it is unsure of what will be added to the ArrayList when passed
from another method. Again, because of how everything works 
together, there shouldn't be an issue; although I am still in 
the process debugging this program, I have yet to find a situation
in which this is an issue.