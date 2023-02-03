package kopo.poly.persistance.mongodb;

import com.mongodb.client.model.Indexes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
@RequiredArgsConstructor

/**
 * 추상화 클래스로 생성
 * 컬렉션 생성 및 삭제 기능 제공하는 함수 생성
 * */
public abstract class AbstractMongoDBComon {
    /**
     * 컬렉션 생성
     *
     * @param mongodb 접속된 MongoDB
     * @param colNm 생성 할 컬렉션명
     * @return 생성 결과
     */
    protected boolean createCollection(MongoTemplate mongodb, String colNm){
        boolean res;

        if(mongodb.collectionExists(colNm)){
            res = false;
        } else {
            mongodb.createCollection(colNm);
            res = true;
        }
        return res;
    }

    /**
     * 인덱스 컬럼 여러 개일때 컬렉션 생성
     *
     * @param mongodb 접속된 MongoDB
     * @param colNm 생성할 컬렉션명
     * @param index 생성할 인덱스
     * @return 생성 결과
     */
    protected boolean createCollection(MongoTemplate mongodb, String colNm, String[] index){

        log.info(this.getClass().getName()+".createCollection Start!");
        boolean res = false;

        // 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 컬렉션이 없는 경우 생성
        if(!mongodb.collectionExists(colNm)){

            // 인덱스 값이 존재한다면..
            if(index.length > 0){
                // 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
                // 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
                mongodb.createCollection(colNm).createIndex(Indexes.ascending(index));
            } else {
                // 인덱스가 없으면 인덱스 없이 컬렉션 생성
                mongodb.createCollection(colNm);
            }

            res = true;
        }
        log.info(this.getClass().getName()+".createCollection Start!");
        return res;
    }
}
