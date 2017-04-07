package command.categorias;

import java.util.Date;
import java.util.List;
import model.Categoria;
import process.cargador.CargadorListaCategoria;
import process.parser.categorias.CategoriaParserJson;

public class CargarCategoriasCommand {
    private final CargadorListaCategoria cargadorListaCategoria;
    private final CategoriaParserJson categoriaParserJson;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    public CargarCategoriasCommand(CargadorListaCategoria cargadorListaCategoria,CategoriaParserJson categoriaParserJson) {
        this.cargadorListaCategoria = cargadorListaCategoria;
        this.categoriaParserJson = categoriaParserJson;
    }

    public List<Categoria> execute(){
       return  categoriaParserJson.parsear(cargadorListaCategoria.cargar());
      }
    
}


