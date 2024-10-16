package com.zero.nlp.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZeroNlpHanlpApplicationTests {

    @Test
    void contextLoads() {
        // 动态增加
        CustomDictionary.add("coder");
        // 强行插入
        CustomDictionary.insert("writer", "nz 1024");

        // 删除词语（注释掉试试）
//        CustomDictionary.remove("coder");
        System.out.println(CustomDictionary.add("learn", "nz 1024 n 1"));
        System.out.println(CustomDictionary.get("learn"));


        String text = "coder want to be a writer, you need to learn a lot";

        // AhoCorasickDoubleArrayTrie自动机扫描文本中出现的自定义词语
        final char[] charArray = text.toCharArray();
        CustomDictionary.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>()
        {
            @Override
            public void hit(int begin, int end, CoreDictionary.Attribute value)
            {
                System.out.printf("[%d:%d]=%s %s\n", begin, end, new String(charArray, begin, end - begin), value);
            }
        });
        System.out.println("########################################");
        // 自定义词典在所有分词器中都有效
        System.out.println(HanLP.segment(text));
    }

}
