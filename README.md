(G)It
==

Distributed product history powered by Git.

## Background
In "The Architecture of Open Source Software Applications - Volume 2" Susan Potter states that
"Git enables the maintenance of a digital body of work (often, but not limited to, code) by many collaborators using a peer-to-peer network of repositories. It supports distributed workflows, allowing a body of work to either eventually converge or temporarily diverge."

## Introduction
Git is, according to Susan Potter, in its structure not limited to exclusively manage source code history. A manufactured physical product does, in a way not very different to source code, collect a kind of history after it has left the store. Just like source code, products can be considered to be "cloned" by customers when they are bought. Information about the product then diverges over time if it is damaged or reviewed by the customer who bought it. Later on the customer's version of the product history can converge with the manufacturer's version and create a merged version containing more information about the product. Could Git be used to manage this kind of product history? In which ways can Git be changed to better manage product history? How could this kind of distributed product history change how we own products?

##Why use a version control system (VCS) for physical products?
>A VCS usually has three core functional requirements, namely:
>
> - Storing content
> - Tracking changes to the content (history including merge metadata)
> - Distributing the content and history with collaborators
>
> [*AOSA*][aosa]

These functionality requirements would in my opinion apply to a product history tool as well. But is it really possible and even necessary to use a VCS for products? Furthermore, is a distributed VCS, like for example Git, really the right tool for keeping track of a product's version history (PVH)?

###Dumb Products
Expensive products with a long expected lifetime, like for example cars, have already been used to being associated with some kind of version history. That is because if you buy a second hand car you would probably want to know the history of the car you are buying. If a system that is simple enough to use would be created this kind of history tracking could be applied to cheaper products as well. You can think of a PVH as a kind of dynamic ad. 

###Smart Products
As the Internet of Things (IoT) is emerging we start seeing products that themselves create data about their usage history. This makes it more feasible that a version history for each product will be maintained since the product itself will generate the data rather than its assumable lazy owner. This data also has its own value and a decent way of storing is therefore of considerable importance. 

###The Environment
Giving each product a digital repository would provide an incentive to not just throw away the product as it gets older. Companies would then be more inclined to produce longer lasting products if the life of these would be increasingly exposed in PVHs. It would also be easier for customer to resell the product if they could provide the new owner with a PVH. 

##Why Distributed?
>Specific benefits of a distributed model include:
>
> - Providing the ability for collaborators to work offline and commit incrementally.
Allowing a collaborator to determine when his/her work is ready to share.
> - Offering the collaborator access to the repository history when offline.
> - Allowing the managed work to be published to multiple repositories, potentially with different branches or granularity of changes visible.
>
>[*AOSA*][aosa]

Personal possessions are closely linked to their owners and integrity is hence key when dealing with their usage history. By using a distributed system like Git to track this history we can use the features mentioned above in order to allow the owners of products to keep their own private history and only share it, in a chosen level of detail, to different external repositories. These external repositories may belong to for example a second-hand buyer of their possession or a manufacturer interested in customer feedback. 

##Content of a Product Version Control System's (PVCS) Repositories 
Rather than source code a PVCS repository could contain content of the following types:

- Transaction history with the price at each transaction
- Photos of the product at different times in its life
- Damages to the product
- Warranties

Example of the tree structure of a PVCS repository:

	root  
	|--Transactions  
	|--Photos  
	|--Damages  
	+--Warranties   



## the Architecture of Git

###Content Storage
Doing so often by a delta based changesets (DBC) or DAG.

DBC flattens content while DAG mirrors the content's file system. Git uses an object database for its DAG and reuses unchanged objects. 

Content is identified by a SHA in Git. 

###Commit and Merge History
Git uses DAG for history as well. 

###Examples to look further into

- Git (VCS)
- Bitcoin (Transaction History)
- Bittorrent (P2P sharing)

[aosa]: http://aosabook.org/en/git.html
    "the Architecture of Open Source Software Applications - vol 2"

## Product history as a directed acyclic graph
Version history is in Git represented as a directed acyclic graph (DAG). Below is my first attempt at representing a product's history as a DAG. 

*under construction*


## Dynamic ownership of products with a distributed product history
Having a product that you own represented as a (G)It repository opens up for new ways of smarter ownership. Here are my initial thoughts about how such an ownership could be represented. 

## Design Goals

- **Functionality:** It should be easier to add a new product to the system than it is to create a second-hand ad on for example Ebay.

- **Functionality:** An added product should be given its own (G)It repository to keep track of its identity and gathered product history

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








