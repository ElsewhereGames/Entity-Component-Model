# Entity Component

## Introduction
While Object Oriented (OO) programming is quite useful in limited contexts, it is often applied in contexts where it makes no sense. For objects which have similar characteristic, OO is fine, but the moment you apply the concepts to complex, compound objects, it limitations become apparent.

This library provides a different means of creating these compound objects. It resolves around two core concepts:

### Entities
Entities are simple objects which have two components:

* A unique identifier
* A collection of components

Note that there is no filter or limit on what components can be assigned to an entity.

### Components
Components are the parts which can make up an entity. There exists only an empty interface for this, no functionality is desired or required.