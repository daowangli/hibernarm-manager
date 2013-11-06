package org.hibernarm.management.util;

import java.sql.Types;

import org.hibernate.dialect.SQLServer2008Dialect;

public class UnicodeSQLServerDialect extends SQLServer2008Dialect {
	public UnicodeSQLServerDialect() {
		super();

		// registerColumnType(Types.BIGINT, "bigint");
		// registerColumnType(Types.BIT, "bit");
		// registerColumnType(Types.CHAR, "nchar(1)");
		// registerColumnType(Types.VARCHAR, 4000, "nvarchar($l)");
		// registerColumnType(Types.VARCHAR, "nvarchar(max)");
		// registerColumnType(Types.VARBINARY, 4000, "varbinary($1)");
		// registerColumnType(Types.VARBINARY, "varbinary(max)");
		// registerColumnType(Types.BLOB, "varbinary(max)");
		registerColumnType(Types.CLOB, "nvarchar(max)");

	}
}
