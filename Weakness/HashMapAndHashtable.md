# HashMap과 Hashtable

Hashtable과 HashMap의 관계는 Vector와 ArrayList의 관계와 동일합니다. 따라서 Hashtable보다 HashMap 사용을 권장하고 HashMap에 대해서만 설명을 진행합니다. 

HashMap은 Map으로 구현했으므로 앞에서 살펴본 Map의 특징, 키(Key)와 값(Value)을 묵어서 하나의 데이터(entry)로 저장한다는 특징이 있습니다. 그래고 해싱(Hashing)을 사용하기 때문에 많은 양의 데이터를 검색하는데 있어서 뛰어난 성능을 보입니다. 다음 코드는 HashMap이 데이터를 어떻게 저장하는지를 확인하기 위한 HashMap의 일부 소스 코드입니다.

```java
public class HashMap extends AbstractMap implements Map, Cloneable, Serializable {
    transient Entry[] table;
    ...
    static class Entry implements Map.Entry {
        final Object key;
        Object value;
        ...
    }
}
```

HashMap은 Entry라는 내부 클래스를 정의하고, 다시 Entry 타입의 배열을 선언하고 있습니다. 키(Key)와 값(Value)은 별개의 값이 아닌 서로 관련된 값이기 때문에 각각의 배열로 선언하기 보다는 하나의 클래스로 정의해서 하나의 배열로 다루는 것이 데이터 무결성(integrity)적인 측면에서 더 바람직하기 때문입니다. 다음 코드는 비객체지향적인 코드와 객체지향적인 코드의 비교입니다.

```java
//비객체지향적인 코드
Object[] key;
Object[] value;

//객체지향적인 코드
Entry[] table;
class Entry {
	Object key;
    Object value;
}
```

HashMap은 키와 값을 각각 Object타입으로 저장합니다. 즉 (Object, Object)의 형태로 저장하기 때문에 어떠한 객체도 저장할 수 있지만 키는 주로 String을 대문자 또는 소문자로 통일해서 사용합니다.

키(key) 컬렉션 내의 키(key) 중에서 유일해야 합니다. 키는 저장된 값을 찾는데 사용되는 것이기 때문에 컬렉션 내에서 유일(unique)해야 합니다. 즉, HashMap에 저장된 데이터를 하나의 키로 검색했을 때 결과가 단 하나이어야 한다는 것을 의미합니다. 만일 하나의 키에 대해 여러 검색결과 값을 얻는다면 원하는 값이 어떤 것인지 알 수 없기 때문입니다. 값(value)은 키(key)와 달리 데이터의 중복을 허용합니다.

 

**HashMap의 생성자와 메서드**

| 생성자 / 메서드                                              | 설명                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| HashMap()                                                    | HashMap객체를 생성                                           |
| HashMap(int initialCapacity)                                 | 지정된 값을 초기용량으로 하는 HashMap객체를 생성             |
| HashMap(int initialCapacity, float loadFactor)               | 지정된 초기용량과 load factor의 HashMap객체를 생성           |
| HashMap(Map m)                                               | 지정된 Map의 모든 요소를 포함하는 HashMap을 생성             |
| void clear()                                                 | HashMap에 저장된 모든 객체를 제거                            |
| Object clone()                                               | 현재 HashMap을 복제해서 반환                                 |
| boolean containsKey(Object key)                              | HashMap에 지정된 키(key)가 포함되어있는지 알려준다. (포함되어 있으면 true) |
| boolean containsValue(Object value)                          | HashMap에 지정된 값(value)가 포함되어있는지 알려준다. (포함되어 있으면 true) |
| Set entrySet()                                               | HashMap에 저장된 키와 값을 엔트리(키와 값의 결합)의 형태로 Set에 저장해서 반환 |
| Object get(Object key)                                       | 지정된 키(key)의 값(객체)을 반환한다. 못찾으면 null 반환     |
| Object getOrDefault(Object key, Object defaultValue)         | 지정된 키(key)의 값(객체)을 반환한다. 키를 못찾으면, 기본값(defaultValue)로 지정된 객체를 반환 |
| boolean isEmpty()                                            | HashMap이 비어있는지 알려준다.                               |
| Set keySet()                                                 | HashMap에 저장된 모든 키가 저장된 Set을 반환                 |
| Object put(Object key, Object value)                         | 지정된 키와 값을 HashMap에 저장                              |
| void putAll(Map m)                                           | Map에 저장된 모든 요소를 HashMap에 저장                      |
| Object remove(Object key)                                    | HashMap에서 지정된 키로 저장된 값(객체)를 제거               |
| Object replace(Object key, Object value)                     | 지정된 키의 값을 지정된 객체(value)로 대체                   |
| boolean replace(Object key, Object oldValue, Object newValue) | 지정된 키와 객체(oldValue)가 모두 일치하는 경우에만 새로운 객체(newValue)로 대체 |
| int size()                                                   | HashMap에 저장된 요소의 개수를 반환                          |
| Collection values()                                          | HashMap에 저장된 모든 값을 컬렉션의 형태로 반환              |

Hashtable은 키(key)나 값(value)으로 null을 허용하지 않지만, HashMap은 키(key)나 값(value)으로 null을 허용합니다. 그래서 map.put(null, null) 이나 map.get(null)과 같이 할 수 있습니다. HashMap은 데이터를 키와 값 모두 Object타입으로 저장하기 때문에 HashMap의 값(value)으로 HashMap을 다시 저장할 수 있습니다. 즉, 하나의 키에 다시 복수의 데이터를 저장할 수 있습니다.

 

## 1. 해싱과 해시함수

- **해싱**: 해시함수(hash function)를 이용해서 데이터를 해시테이블(hash table)에 저장하고 검색하는 기법.

해시함수는 데이터가 저장되어 있는 곳을 알려 주기 때문에 다량의 데이터 중에서도 원하는 데이터를 빠르게 찾을 수 있습니다. 해싱을 구현한 컬렉션 클래스로는 HashSet, HashMap, Hashtable 등이 있습니다. Hashtable은 컬렉션 프레임웍이 도입되면서 HashMap으로 대체되었으나 이전 소스와의 호환성 문제로 남겨 두고 있으므로, 가능하면 Hashtable 대신 HashMap을 사용하는 것이 좋습니다.

 해싱에서 사용하는 자료구조는 배열과 링크드 리스트의 조합으로 되어 있습니다. 저장할 데이터의 키를 해시함수에 넣으면 배열의 한 요소를 얻게 되고, 다시 그 곳에 연결되어 있는 링크드 리스트에 저장하게 됩니다. 즉, 배열의 각 요소에는 링크드 리스트가 저장되어 있어서 실제 저장한 데이터는 링크드 리스트에 담겨지게 됩니다.



<img src="https://blog.kakaocdn.net/dn/81WV8/btrqGTgRZXm/9DXPxX75GkIdRKB5JUIXy0/img.png" alt="img" style="zoom:33%;" />



 

## 2. HashMap에 저장된 데이터를 찾는 과정

<img src="https://blog.kakaocdn.net/dn/dsXqFo/btrqI49V8fs/EUKZuIJ6niRSoj1YjTWI21/img.png" alt="img" style="zoom:30%;" />

1. 검색하고자 하는 값의 키로 해시함수를 호출합니다.
2. 해시함수의 계산결과인 해시코드를 이용해서 해당 값이 저장되어 있는 링크드 리스트를 찾습니다.
3. 링크드 리스트에서 검색한 키와 일치하는 데이터를 찾습니다.

링크드 리스트는 검색에 불리한 자료구조이기 때문에 링크드 리스트의 크기가 커질수록 검색속도가 떨어지게 됩니다. 반면에 배열은 배열의 크기가 커져도, 원하는 요소가 몇 번째에 있는지만 알면 공식에 의해 빠르게 원하는 값을 찾을 수 있습니다.

- `배열의 n번째 요소의 주소 = 배열의 시작주소 + type의 size * n`

하나의 링크드 리스트에 최소한의 데이터만 저장되려면, 저장될 데이터의 크기를 고려해서 HashMap의 크기를 적절하게 지정해주어야 하고, 해시함수가 서로 다른 키에 대해서 중복된 해시코드의 반환을 최소화해야 합니다. 그래서 HashMap에서 빠른 검색시간을 얻을 수 있습니다. 그렇기 때문에 해싱을 구현하는 과정에서 제일 중요한 것은 해시함수의 알고리즘입니다. 

HashMap과 같이 해싱을 구현한 컬렉션 클래스에서는 주로 Object클래스에 정의된 hashCode()를 해시함수로 사용합니다. Object클래스에 정의된 hashCode()는 객체의 주소를 이용하는 알고리즘으로 해시코드를 만들어 내기 때문에 모든 객체에 대해 hashCode()를 호출한 결과가 서로 유일한 훌륭한 방법입니다.

String클래스의 경우 Object로부터 상속받은 hashCode()를 오버라이딩해서 문자열의 내용으로 해시코드를 만들어 냅니다. 서로 다른 String인스턴스일지라도 같은 내용의 문자열을 가졌다면 hashCode()를 호출하면 같은 해시코드를 얻습니다. 서로 다른 두 객체에 대해 equals()로 비교한 결과가 true인 동시에 hashCode()의 반환값이 같으면 같은 객체로 인식합니다. HashMap에서도 같은 방법으로 객체를 구별하며, 이미 존재하는 키에 대한 값을 저장하면 기존의 값을 새로운 값으로 덮어씁니다.

equals()를 오버라이딩(재정의) 해야 한다면 hashCode()도 같이 재정의해서 equals()의 결과가 true인 두 객체의 해시코드(hashCode()의 결과값)가 항상 같도록 해주어야 합니다. 그렇지 않으면 HashMap과 같이 해싱을 구현한 컬렉션 클래스에서는 equals()의 호출결과가 true지만 해시코드가 다른 두 객체를 서로 다른 것으로 인식하고 따로 저장합니다.  equals()로 비교한 결과가 false이고 해시코드가 같은 경우는 같은 링크드 리스트에 저장된 서로 다른 두 데이터가 됩니다.

 