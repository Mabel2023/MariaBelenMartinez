package com.distribuida;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class servidor {
    public static void main (String[] args){
        io.helidon.microprofile.cdi.Main.main(args);

    }
}
