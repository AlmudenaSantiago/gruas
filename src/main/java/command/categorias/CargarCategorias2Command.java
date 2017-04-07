package command.categorias;

import java.util.Date;
import java.util.List;
import model.Categoria;
import process.cargador.CargadorListaCategoria2;
import process.parser.categorias.CategoriaParserJson;

public class CargarCategorias2Command {
    private final CargadorListaCategoria2 cargadorListaCategoria;
    private final CategoriaParserJson categoriaParserJson;
    private Date fechaDesde;
    private Date fechaHasta;
   
    
    public CargarCategorias2Command(CargadorListaCategoria2 cargadorListaCategoria,CategoriaParserJson categoriaParserJson) {
        this.cargadorListaCategoria = cargadorListaCategoria;
        this.categoriaParserJson = categoriaParserJson;
    }

    public List<Categoria> execute(Integer id){
        return categoriaParserJson.parsearCategorias2(cargadorListaCategoria.cargar(id));
      }
    
}


