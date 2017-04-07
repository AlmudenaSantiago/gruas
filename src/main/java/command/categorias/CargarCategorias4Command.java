package command.categorias;

import java.util.List;
import model.Categoria;
import process.cargador.CargadorListaCategoria4;
import process.parser.categorias.CategoriaParserJson;

public class CargarCategorias4Command {
    private final CargadorListaCategoria4 cargadorListaCategoria;
    private final CategoriaParserJson categoriaParserJson;
   
   
    
    public CargarCategorias4Command(CargadorListaCategoria4 cargadorListaCategoria,CategoriaParserJson categoriaParserJson) {
        this.cargadorListaCategoria = cargadorListaCategoria;
        this.categoriaParserJson = categoriaParserJson;
    }

    public List<Categoria> execute(Integer id){
        return categoriaParserJson.parsearCategorias4(cargadorListaCategoria.cargar(id));
      }
    
}


