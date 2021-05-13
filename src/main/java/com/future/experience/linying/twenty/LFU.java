package com.future.experience.linying.twenty;

/**
 *
 总共一小时常规地先问5分钟基础知识：对比不同的cache policy，描述virtual memory和page
 然后做题，类似于LFU，实现数据结构来count frequency，接口包括increment(key), getMin(), getMax()，就是用frequency来做index

 LFU Thoughts:
 Each entry includes: key, val, counter
 - put(key, val)
    - if key is presented, update value, inc counter
    - else
        - if cache is ful, evict it first.
        - insert entry and init counter = 1
 - get(key)
    - return value and increment counter if key is presented.
 - evict()
    - find min freq entry
    - remove the entry

 get: HashMap for key value, key -> DoublyLinkedNode(value)
 freq map: HashMap for counter, counter -> entries (LinkedHashSet)
 entry freq: HashMap



 */
public class LFU {
}
