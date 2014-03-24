## Lingo
- *ISIM* Item Specific Information Management
- *PLM* Product Life Cycle Management
- *BOL, MOL and EOL* The beginning, middle and end of a product's life
- *Product Avatar* Product-item-specific software and data that can be stored locally with the product itself or be located on one or more remote computers.
- *GUPI* Globally Unique Product Identifier. The minimal information included in the product avatar. Sufficient information for retrieving the Internet address(es) where the remote parts of the avatar is located.
- *EPC* Electronic Product Code
- *PLIM* Product Lifetime Information Management
- *SCM* Supply Chain Management
- *PEID* Product Embedded Information Device
- *ID@URI* An identifier format proposed by the DIALOG research project of the Helsinki University of Technology for identifying and linking tangible "things" to their information sources on the Internet (the "Internet of Things").

##Translating Git to It
word | examples common to Git | examples in It
---- | --------------- | --------------
*collaborators* | software developers | manufacturer, resellers, customers and perhaps the product itself
*repository* | contains source code stored as a version history | contains product information stored as a version history
*clone* | start manipulating existing source code | retrieving product information when you buy a product from a reseller or private person
*commit* | state of the source code worth remembering | state of the product worth remembering?



##Proposed changes that could turn Git into It



## Pros and Cons of Using Git for product information

**+** 
Cloning product information repositories would be a good way for customer to store information about their bought products.

**+** 
Fast due to that most operations run locally and that it's written in C.
 
**+**
Well established standard for potential third-party developers to work with. 

**-**
Is there really any purpose in having the ability to move backwards in a products version history?!

**-**
Unnecessarily complex UI?

**-** 
How to separate item specific information (ISI) and general product information (GPI)? Use special merging strategies that takes this into account? 

**-**
Git is based on a developer's work-flow. Could this be mapped or simplified somehow to resemble that of a git-illiterate customer using a GUI? (for example the staging area feature)

##Notes from the paper about ISIM at Caterpillar

Draw PLM process flow diagram of a couple of different products? Use existing diagrams from companies? The CAT process is cyclic - at EOL engines are rebuilt into Reman engines and a new BOL is recorded.

350-500 Nodes of the ISIM network!
- Dealers 
- Distributors
- Designers
- Logistics

> For keeping track of distributed item-specific information, the product avatar can explicitly store references e.g. using semantic relationships such as "part off", "wants-location-updates" etc. as explained in 


##Notes from Främling's paper about an Information Architecture for the IoT
>The success of the information architecture and the implementations of it are in this paper evaluated mainly based on the following criteria: 

> -  Degree of fulfillment of the requirements set up by the demonstrator owners. 
> -  Scalability. 
> -  Flexibility of the system when organizational, hardware or other changes occurs. 
> -  To what extent it satisfies the requirements of an Internet of Things. 
> -  To what extent it satisfies the requirements of PLIM. 

Främling is focused on the data that a ubicomp device generates rather than the current version of it's product avatar. Could my It approach support storage of such data and allow for other applications to generate and communicate that data over time? 

>The PROMISE connectivity model is similar to that of the Internet itself. Where the 
Internet uses the HTTP protocol for transmitting HTML-coded information mainly 
intended for human users, PROMISE uses the PROMISE Messaging Interface (PMI) for 
transmitting XML-coded information mainly intended for automatic processing by 
information systems. It is important to understand these relationships because PROMISE 
in effect proposes an extension to the Internet itself. 






