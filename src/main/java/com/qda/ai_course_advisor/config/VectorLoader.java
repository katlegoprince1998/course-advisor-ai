package com.qda.ai_course_advisor.config;

import com.qda.ai_course_advisor.globals.Constant;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.List;

@Configuration
public class VectorLoader {

    @Value("classpath:sa_courses_10000.pdf")
    private Resource resource;

    @Bean
    SimpleVectorStore simpleVectorStore(EmbeddingModel model){
        File vectorStoreFile = new File(Constant.VECTOR_STORE_FILE);
        SimpleVectorStore vectorStore = new SimpleVectorStore(model);

        if (vectorStoreFile.exists()){
            System.out.println("LOADED VECTOR STORE FILE SUCCESSFULLY");
            vectorStore.load(vectorStoreFile);
        }else{
            System.out.println("CREATING VECTOR STORE");
            PdfDocumentReaderConfig config = PdfDocumentReaderConfig
                    .builder()
                    .withPagesPerDocument(1)
                    .build();

            PagePdfDocumentReader pagePdfDocumentReader = new PagePdfDocumentReader(resource, config);

            var textSplitter = new TokenTextSplitter();
            List<Document> docs = textSplitter.apply(pagePdfDocumentReader.get());

            vectorStore.add(docs);
            vectorStore.save(vectorStoreFile);
            System.out.println("VECTOR STORE CREATED SUCCESSFULLY");
        }
        return vectorStore;
    }
}
