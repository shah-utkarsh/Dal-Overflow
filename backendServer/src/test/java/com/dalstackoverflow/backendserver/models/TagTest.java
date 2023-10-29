package com.dalstackoverflow.backendserver.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagTest {

    @Test
    public void testGettersAndSetters() {
        Tag tag = new Tag();
        tag.setTagID(1);
        tag.setQuestionID(2);
        tag.setTagName("test tag");

        assertEquals(1, tag.getTagID());
        assertEquals(2, tag.getQuestionID());
        assertEquals("test tag", tag.getTagName());
    }
}