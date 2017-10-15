# learning-rxjava

Observable is like a stream of events.

"_unlike Iterator, we can not pull data manually... Observable must push data as it comes_"

**Rx Design Guidelines define this rule:** OnNext* (OnCompleted | OnError)?

OnNext OnCompleted => emits one value an terminates with successful

OnNext+ OnCompleted => emits multiple events before terminate

OnNext+ => Infinite list of events

OnCompleted or OnError => ?

OnNext+ OnError => infinte stream events failling

"_As a matter of fact Observer is the core abstraction for listening in RxJava. Yet if you want even greater control, Subscriber (Observers abstract implementation) is even more powerful._"

"_Of course, you must keep in mind that cache() plus infinite stream is the recipe for a disaster_"

## Operations

# flatMap
> _What flatMap() essentially does is take a master sequence (Observable) of values appearing over time (events) and replaces each of the events with an independent subsequence. These subsequences are generally unrelated to one another and to the event that generated them from master sequence. To make it clear, you no longer have a single the master sequence but a set of Observables, each working on its own, coming and going over time. Therefore, flatMap() cannot give any guarantee about what order of those subevents will arrive at the downstream operator/subscriber._


## About C10k problem
> The classic thread per connection model struggles to solve the C10k problem. With 10,000 threads we do the following:
Consume several gigabytes of RAM for stack space
Put great pressure on the garbage collection mechanism, despite that stack space is not garbage-collected (lots of GC roots and live objects)
Waste significant amount of CPU time simply switching cores to run different threads (context switching).
