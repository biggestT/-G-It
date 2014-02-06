it
==

Distributed digital ownership of "dumb" physical things

## Design Goals
- **Functionality:** It should be easier to add a new thing to the system than it is to create a second-hand ad on for example Ebay.

- **Functionality:** An added thing should be given a persistent digital profile (preferably in the shape of one single file).

- **Functionality:** A user should be able to lend, borrow and sell things to trusted friends.

- **Environment:** The system should be designed to promote local exchanges between friends in order to avoid unnecessary transports.

- **Integrity:** Users should themselves have control over the data they generate.

## Example Graph
![it graph example](itGraph.png)

## Nodes and Relationships
The system consists of persons, things, tags and images that are connected in a directed graph.

### User
- When a user is created it is assigned a public-private key pair. The public key is added as an attribute to the user node where as the private key is stored in a special file on that user's device.

### Thing
- Is owned by one or more users.
- Can be connected to one or more images.
- Can be connected to one or more tags.
- Has an optional price attribute.
- *The price can be used to control the visibility of the thing. Perhaps you only want your friends to see things worth less than 500$ and friends of friends to only see things worth less than 100$.*
- *Can be created when a user purchases something on-line or by taking a photo of an already owned possession.*
- *A thing and its children can be serialized and stored in a file.*

### Image
- Portrays one thing.
- *Can possibly be used to identify the thing it portrays. Efficient object recognition might be possible if the search is limited to only one user's possessions.* 

### Tag
- Has a name attribute stored as a string.
- *Can be connected to a category by the use of for example a dictionary graph.*

### Ownership
- Is a one-way directed relationship between one or more users and one thing.
- Has a starting date attribute.
- **Borrowing:** Add an end date.
- **Renting:** Add a price per day and an end date.

## Problems to Solve
- How should things be easily identified? *Object recognition in images?*
- How can your graph be shared with friends without you loosing control over its content? *Encryption? Impossible?!*
- How can your graph be read while you are off-line? *Make your friends act as a server serving content from your graph to your friends? Read up on P2P architectures!*








