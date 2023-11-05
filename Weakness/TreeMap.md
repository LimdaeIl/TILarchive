# TreeMap

이진검색트리의 형태로 키와 값의 쌍으로 이루어진 데이터를 저장한다. 검색과 정렬에 적합한 컬렉션 클래스이다.

검색에 관한 대부분의 경우 HashMap이 TreeMap보다 더 뛰어나지만, 범위검색이나 정렬이 필요한 경우에는 TreeMap의 성능이 더 뛰어나다.

 

TreeMap의 생성자와 메서드

| 메서드                                                       | 설명                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| TreeMap()                                                    | TreeMap객체를 생성                                           |
| TreeMap(Comparator c)                                        | 지정된 Comparator를 기준으로 정렬하는 TreeMap객체를 생성     |
| TreeMap(Map m)                                               | 주어진 Map에 저장된 모든 요소를 포함하는 TreeMap을 생성      |
| TreeMap(SortedMap m)                                         | 주어진 SortedMap에 저장된 모든 요소를 포함하는 TreeMap을 생성 |
| Map.Entry ceilingEntry(Object key)                           | 지정된 key와 일치하거나 큰 것중 제일 작은 것의 키와 값의 쌍(Map.Entry)를 반환. 없으면 null을 반환 |
| Object ceilingKey(Object key)                                | 지정된 key와 일치하거나 큰 것중 제일 작은 것의 키를 반환. 없으면 null을 반환 |
| void clear()                                                 | TreeMap에 저장된 모든 객체를 제거                            |
| Object clone()                                               | 현재 TreeMap을 복제해서 반환                                 |
| Comparator comparator()                                      | TreeMap의 정렬기준이 되는 Comparator를 반환 Comparator가 지정되지 않았다면 null을 반환 |
| boolean containsKey(Object key)                              | TreeMap에 지정된 키(key)가 포함되어있는지 알려줌 (포함되어 있으면 true) |
| boolean containsValue(Object value)                          | TreeMap에 지정된 값(value)가 포함되어있는지 알려줌 (포함되어 있으면 true) |
| NavigableSet descendingKeySet()                              | TreeMap에 저장된 키를 역순으로 정렬해서 NavigableSet에 담아서 반환 |
| Set entrySet()                                               | TreeMap에 저장된 키와 값을 엔트리(키와 값의 결합)의 형태로 Set에 저장해서 반환 |
| Map.Entry firstEntry()                                       | TreeMap에 저장된 첫번째 (가장 작은) 키와 값의 쌍(Map.Entry)을 반환 |
| Object firstKey()                                            | TreeMap에 저장된 첫번째 (가장 작은) 키를 반환                |
| Map.Entry floorEntry(Object key)                             | 지정된 key와 일치하거나 작은 것 중에서 제일 큰 키의 쌍(Map.Entry)을 반환. 없으면 null을 반환 |
| Object floorKey(Object key)                                  | 지정된 key와 일치하거나 작은 것 중에서 제일 큰 키를 반환. 없으면 null을 반환 |
| Object get(Object key)                                       | 지정된 키(key)의 값(객체)을 반환                             |
| SortedMap headMap(Object toKey)                              | TreeMap에 저장된 첫번째 요소부터 지정된 범위에 속한 모든 요소가 담긴 SortedMap을 반환(toKey는 미포함) |
| NavigableMap headMap(Object toKey, boolean inclusive)        | TreeMap에 저장된 첫번째 요소부터 지정된 범위에 속한 모든 요소가 담긴 SortedMap을 반환. inclusive의 값이 true면 toKey도 포함 |
| Map.Entry higherEntry(Object key)                            | 지정된 key보다 큰 키 중에서 제일 작은 키의 쌍(Map.Entry)을 반환. 없으면 null을 반환 |
| Object higherKey(Object key)                                 | 지정된 key보다 큰 키 중에서 제일 작은 키를 반환. 없으면 null을 반환 |
| boolean isEmpty()                                            | TreeMap이 비어있는지 알려준다.                               |
| Set keySet()                                                 | TreeMap에 저장된 모든 키가 저장된 Set을 반환                 |
| Map.Entry lastEntry()                                        | TreeMap에 저장된 마지막 키(가장 큰 키)의 쌍을 반환           |
| Object lastKey()                                             | TreeMap에 저장된 마지막 키(가장 큰 키)를 반환                |
| Map.Entry lowerEntry(Object key)                             | 지정된 key보다 작은 키 중에서 제일 큰 키의 쌍(Map.Entry)을 반환. 없으면 null을 반환 |
| Object lowerKey(Object key)                                  | 지정된 key보다 작은 키 중에서 제일 큰 키를 반환. 없으면 null을 반환 |
| NavigableSet navigableKeySet()                               | TreeMap의 모든 키가 담긴 NavigableSet을 반환                 |
| Map.Entry pollFirstEntry()                                   | TreeMap에서 제일 작은 키를 제거하면서 반환                   |
| Map.Entry pollLastEntry()                                    | TreeMap에서 제일 큰 키를 제거하면서 반환                     |
| Object put(Object key, Object value)                         | 지정된 키와 값을 TreeMap에 저장                              |
| void putAll(Map map)                                         | Map에 저장된 모든 요소를 TreeMap에 저장                      |
| Object remove(Object key)                                    | TreeMap에 지정된 키로 저장된 값(객체)를 제거                 |
| Object replace(Object k, Object v)                           | 기존의 키(k)의 값을 지정된 값(v)으로 변경                    |
| boolean replace(Object key, Object oldValue, Object newValue) | 기존의 키(k)의 값을 새로운 값(newValue)으로 변경 단, 기존의 값과 지정된 값(oldValue)이 일치해야함 |
| int size()                                                   | TreeMap에 저장된 요소의 개수를 반환                          |
| NavigableMap subMap(Object fromKey, boolean fromInclusive, Object toKey, boolean toInclusive) | 지정된 두개의 키 사이에 있는 모든 요소들이 담긴 NavigableMap을 반환. fromInclusive가 true면 범위에 fromKey 포함. toInclusive가 true면 범위에 toKey 포함 |
| SortedMap subMap(Object fromKey, Object toKey)               | 지정된 두 개의 키 사이에 있는 모든 요소들이 담긴 SortedMap을 반환 (toKey는 포함되지 않는다.) |
| SortedMap tailMap(Object fromKey)                            | 지정된 키부터 마지막 요소의 범위에 속한 요소가 담긴 SortedMap을 반환 |
| NavigableMap tailMap(Object fromKey, boolean inclusive)      | 지정된 키부터 마지막 요소의 범위에 속한 요소가 담긴 NavigableMap을 반환. inclusive가 true면 fromKey 포함 |
| Collection values()                                          | TreeMap에 저장된 모든 값을 컬렉션의 형태로 반환              |