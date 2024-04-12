isolation.level이 read_committed으로 설정된 컨슈머가 특정 상황에서 더 이상 데이터를 읽어오지 못하는 상황을 재현한 코드입니다. 
해당 재현을 위해서는 실행중인 카프카가 필요하며, 버전은 transactional producer를 지원한다면 상관없습니다.

재현 방법은 다음과 같습니다.

   1. /src/main/java/org/example/KafkaConsumerMain.java와 동일 패키지의 KafkaReadUnCommittedConsumerMain.java를 실행합니다.
   2. 마찬가지로 동일 패키지의 KafkaProducerMain.java를 실행하고, 1번에서 실행한 두 개의 컨슈머에서 메시지가 들어온 것을 확인합니다.
   3. KafkaProducerUnFinishedMain.java를 실행하고, 다시 한 번 KafkaProducerMain.java를 실행합니다.
   4. 2번과는 다르게, KafkaConsumerMain에서만 로그가 발생하는 것을 확인합니다.
