/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

/**
 *
 * @author tfano_000
 */
public interface Observer {
    public void update(String str);
    public void update(Boolean fini);
    public void updateBombe(int x, int y, boolean mort);
    public void updateCase(int x, int y);
}