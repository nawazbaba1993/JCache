# CACHE
cache implementation in java

There are different types of chahing mechanism like LRU, LIFO etc. In this we will try to implement those using java.

## 1. LRU CACHE:

LRU represents "Least Recent Used", which means which ever data is accessed recently will move to top of cache data. If cache size is full then the data which is access least is removed.

Now the which ever we are going to develop need to do the following
1. Add data into the cache in constant time (i.e.,O(1)).
2. Fetch the data in constant time (i.e.,O(1)).
3. Remove data in constant time (i.e.,O(1)).

To achive this we are going to use HashTable and doubly linkedlist as our data storage.

### Why two data structures(HashTable and doubly linkedlist)?
Before answering this lets see what features individually these data structures provide us.
**1. HashTable:** Best time complexity for fetch, insert, remove is O(1). 
**2. Doubly linkedlist:** Provides access to it's neighbour in O(1). Best time complexity for fetch is O(n) and insert, remove is O(1). 

Now if we use only **HashTable** then we can't keep track of which data is accessed recently and move it to top. So we use **Doubly linkedlist** to store our cache data and pointer to the data in HashTable. By using both the data structers we achive fetch, insert and remove in O(1).
  
### FILES

1. CacheNode : This acts a node for our  **Doubly linkedlist**
2. Cache : This acts as a data store for our cache.
3. GenricCacheServiceImpl: It provide functionality of storing, accessing and removing data from cache.
4. LRUEvictionPolicy: Evicts the cache which is least accessed from the cache if its full.
5. CacheDriver: Sample driver class to demo the cache implementation.

### Setps to use:
1. Clone the repo.
2. Run as java application.

### Sample data output: 
Data which is most recently accessed is printed first and so on.
```
CACHE AFTER ADDING DATA
4-E
3-D
2-B
1-A
CACHE AFTER MODIFYING EXISTING DATA
3-C
4-E
2-B
1-A
CACHE AFTER ADDING MORE THAN ITS SIZE DATA
Cache is full calling eviction policy to evict cache
5-F
3-C
4-E
2-B
GETTING EXISTING DATA
C
CACHE AFTER GETTING EXISTING DATA
3-C
5-F
4-E
2-B
CACHE AFTER REMOVING EXISTING DATA
3-C
5-F
2-B
```
