/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;

public interface ICRUD<T> {
    void inserir(T objeto);
    void atualizar(T objeto);
    T buscarPorId(Object id);  
    List<T> listarTodos();
}

