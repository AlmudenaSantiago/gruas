package command;

import java.util.Date;

public interface Command {

    public void execute();

    public void execute(Date fechaDesde, Date fechaHasta);

}
