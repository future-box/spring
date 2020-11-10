
# 6. AOP
## 6.1 트랜잭션 코드의 분리
### 6.1.1 메소드 분리
- 트랜잭션 경계설정 코드와 비지니스 코드 분리
### 6.1.2 DI를 이용한 클래스의 분리
- 트랜잭션 코드와 비지니스 코드는 서로 주고받는 정보가 없다. 클래스로 분리되어도 된다.  
__DI 적용을 이용한 트랜잭션 분리__  
UserService   
UserServiceImpl   UserServiceTx   
__UserService 인터페이스 도입__
```java
public interface UserService {
    void add(User user);
    void upgradeLevels();
}
```
__분리된 트랜잭션 기능__
```java
@Override
public void upgradeLevels() {
    TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    try {
        
        userService.upgradeLevels();
        
        this.transactionManager.commit(status);
    } catch (RuntimeException e) {
        this.transactionManager.rollback(status);
        throw e;
    }
}
```
__트랜잭션 적용을 위한 DI 설정__

__트랜잭션 경계설정 코드 분리의 장점__
1. 비지니스 로직을 담당하고 있는 UserServiceImpl 코드를 작성할 때 트랜잭션과 같은 기술적인 내용에는 신경 쓰지 않아도 된다.
2. 비지니스 로직에 대한 테스트를 손쉽게 만들 수 있다.
## 6.2 고립된 단위 테스트