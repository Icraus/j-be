## Welcome to JCustomer

# installtion and how to use
Get started by reading this [README](https://github.com/Icraus/j-be/blob/master/README.md).

![uml Diagaram](/class.png)
![class Diagaram](/uml.png)

# Notes about the design:
- First I have to say that I would have chosen other approaches to deal with these tasks as this is not a production code.
- In production, I would choose other approaches to handle the country stuff differently
So for example, I would choose GraphQL instead of a REST API to do the queries and mutations graphQL gives you more flexibility, and you can actually relay on different approach instead of REST/Http commands you can implement it using Websockets, also this would be more helpful to solve the country/code/is valid problem without having to implement it this way
- I would use SQL views instead of normal queries(select) which I can use regex(which is supported by SQLite3) to get country, code and valid easier.
- I would use docker swarm or kubernetes to manage the service instead of normal docker containers.
- I would have used git submodules to manage dependencies between the three projects.
