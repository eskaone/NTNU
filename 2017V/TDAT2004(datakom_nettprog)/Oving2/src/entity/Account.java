package entitet1;

import java.util.*;
import javax.persistence.*;
import java.io.*;

/**
 * Created by wizard man on 06.03.2017.
 */
@Entity
public class Account implements Serializable {
    @Id
    private long accNr;
    private double balance;
    
}
