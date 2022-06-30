/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package com.manning.javapersistence.springdatamongodb.template;

import com.manning.javapersistence.springdatamongodb.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SaveUpdateTest extends SpringDataMongoDBApplicationTests {

    @Test
    void testSaveUpdate() {
        User user = mongoTemplate.findOne(
                Query.query(Criteria.where("level").is(1)), User.class);
        user.setLevel(2);
        mongoTemplate.save(user, "user");

        Query query = new Query();
        query.addCriteria(Criteria.where("level").is(1));

        List<User> users = mongoTemplate.find(query, User.class);

        assertEquals(1, users.size());

    }
}
