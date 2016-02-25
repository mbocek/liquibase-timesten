/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package liquibase.datatype.ext

import liquibase.database.ext.TimestenDatabase
import liquibase.datatype.core.VarcharType;
import spock.lang.Specification
import spock.lang.Unroll;

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
class TimestenVarcharTypeTest extends Specification {
    
    @Unroll
	def "test data type #expected"() {
		when:
		def type = new TimestenVarcharType()
		for (param in params) {
			type.addParameter(param)
		}

		then:
        assert type instanceof VarcharType
		type.toDatabaseDataType(database).toString() == expected

		where:
		params       | database               | expected
		[13]         | new TimestenDatabase() | "VARCHAR2(13)"
		[13, "BYTE"] | new TimestenDatabase() | "VARCHAR2(13, BYTE)"
		[13, "CHAR"] | new TimestenDatabase() | "VARCHAR2(13, CHAR)"
	}
}
