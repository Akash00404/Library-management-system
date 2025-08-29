import java.time.*;
import java.sql.Date;

class Util {
static java.sql.Date toSql(LocalDate d){ return Date.valueOf(d); }
static LocalDate toLd(java.sql.Date d){ return d==null?null:d.toLocalDate(); }
static int daysLate(LocalDate due, LocalDate ret){
if (ret==null || !ret.isAfter(due)) return 0;
return (int)java.time.temporal.ChronoUnit.DAYS.between(due, ret);
}
}