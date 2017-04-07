package command.categorias;

import java.util.List;
import model.Categoria;
import process.cargador.CargadorListaCategoria3;
import process.parser.categorias.CategoriaParserJson;

public class CargarCategorias3Command {
    private final CargadorListaCategoria3 cargadorListaCategoria;
    private final CategoriaParserJson categoriaParserJson;
   
   
    
    public CargarCategorias3Command(CargadorListaCategoria3 cargadorListaCategoria,CategoriaParserJson categoriaParserJson) {
        this.cargadorListaCategoria = cargadorListaCategoria;
        this.categoriaParserJson = categoriaParserJson;
    }

    public List<Categoria> execute(Integer id){
        return categoriaParserJson.parsearCategorias3(cargadorListaCategoria.cargar(id));
      }
    
}


