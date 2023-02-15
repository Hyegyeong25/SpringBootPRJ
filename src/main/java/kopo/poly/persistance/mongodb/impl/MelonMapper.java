package kopo.poly.persistance.mongodb.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import kopo.poly.dto.MelonDTO;
import kopo.poly.persistance.mongodb.AbstractMongoDBComon;
import kopo.poly.persistance.mongodb.IMelonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MelonMapper extends AbstractMongoDBComon implements IMelonMapper {
    private final MongoTemplate mongodb;

    @Override
    public int insertSong(List<MelonDTO> pList, String colNm) throws Exception {
        log.info(this.getClass().getName()+".insertSong Start!");

        int res = 0;

        if(pList == null){
            pList = new LinkedList<>();
        }

        //데이터를 저장할 컬렉션 생성
        super.createCollection(mongodb, colNm, "collectTime");

        //저장할 컬렉션 객체 생성
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        for(MelonDTO pDTO : pList){
            if(pDTO == null){
                pDTO = new MelonDTO();
            }

            //레코드 한개씩 저장하기
            col.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        }

        res = 1;

        log.info(this.getClass().getName()+".insertSong End!");
        return 0;
    }

    @Override
    public List<MelonDTO> getSongList(String colNm) throws Exception {
        log.info(this.getClass().getName()+".getSongList Start!");
        log.info(this.getClass().getName()+".getSongList End!");
        return null;
    }

    @Override
    public List<MelonDTO> getSingerSongCnt(String colNm) throws Exception {
        log.info(this.getClass().getName()+".getSingerSongCnt Start!");
        log.info(this.getClass().getName()+".getSingerSongCnt End!");
        return null;
    }
}
