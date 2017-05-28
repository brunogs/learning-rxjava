# learning-rxjava

Observable is like a stream of events.

"_unlike Iterator, we can not pull data manually... Observable must push data as it comes_"

**Rx Design Guidelines define this rule:** OnNext* (OnCompleted | OnError)?

OnNext OnCompleted => emits one value an terminates with successful

OnNext+ OnCompleted => emits multiple events before terminate

OnNext+ => Infinite list of events

OnCompleted or OnError => ?

OnNext+ OnError => infinte stream events failling

