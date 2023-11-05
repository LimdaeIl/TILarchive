# Properties

Properties는 HashMap의 구버전인 Hashtable을 상속받아 구현한 것으로, Hashtable은 키와 값을 (Object, Object)의 형태로 저장하는데 비해 Properties는 (String, String)의 형태로 저장하여 보다 단순화된 컬렉션클래스라 할 수 있다.

주로 애플리케이션의 환경설정과 관련된 속성(property)을 저장하는데 사용되며 데이터를 파일로부터 읽고 쓰는 편리한 기능을 제공한다. 

 

Properties는 Hashtable을 상속받아 구현한 것이라 Map의 특성상 저장순서를 유지하지 않는다. 또한 컬렉션프레임웍 이전의 구버전이므로 Iterator가 아닌 Enumeration을 사용한다.

 

Properties의 생성자와 메서드

| 메서드                                                       | 설명                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| Properties()                                                 | Properties객체를 생성한다.                                   |
| Properties(Properties defaults)                              | 지정된 Properties에 저장된 목록을 가진 Properties객체를 생성한다. |
| String getProperty(String key)                               | 지정된 키(key)이 값(value)을 반환한다.                       |
| String getProperty(String key, String defaultValue)          | 지정된 키(key)의 값(value)을 반환한다. 키를 못찾으면 defaultValue를 반환한다. |
| void list(PrintStream out)                                   | 지정된 PrintStream에 저장된 목록을 출력한다.                 |
| void list(PrintWriter out)                                   | 지정된 PrintWriter에 저장된 목록을 출력한다.                 |
| void load(InputStream inStream)                              | 지정된 InputStream으로부터 목록을 읽어서 저장한다.           |
| void load(Reader reader)                                     | 지정된 Reader으로부터 목록을 읽어서 저장한다.                |
| void loadFromXML(InputStream in)                             | 지정된 InputStream으로부터 XML문서를 읽어서, XML문서에 저장된 목록을 읽어다 담는다. (load & store) |
| Enumeration propertyNames()                                  | 목록의 모든 키(key)가 담긴 Enumeration을 반환한다.           |
| void save(OutputStream out, String header)                   | deprecated되었으므로 store()를 사용할 것.                    |
| Object setProperty(String key, String value)                 | 지정된 키와 값을 저장한다. 이미 존재하는 키(key)면 새로운 값(value)으로 바뀐다. 기존에 같은 키로 저장된 값이 있는 경우 그 값을 Object타입으로 반환하며, 그렇지 않을 때는 null을 반환 |
| void store(OutputStream out, String comments)                | 저장된 목록을 지정된 OutputStream에 출력(저장)한다. comments는 목록에 대한 주석으로 저장된다. |
| void storeToXML(OutputStream os, String comment)             | 저장된 목록을 지정된 출려스트림에 XML문서로 출력(저장)한다. comment는 목록에 대한 설명(주석)으로 저장된다. |
| void storeToXML(OutputStream os, String comment, String encoding) | 저장된 목록을 지정된 출력스트림에 해당 인코딩의 XML문서로 출력(저장)한다. comment는 목록에 대한 설명(주석)으로 저장된다. |
| Set stringPropertyNames()                                    | Properties에 저장되어 있는 모든 키(key)를 Set에 담아서 반환한다. |