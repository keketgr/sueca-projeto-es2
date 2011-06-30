/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uff.es.pkg.sueca.factory;

/**
 *
 * @author Goop
 */
public class Factory {

    public static IGame getGame() {
        return new Game();
    }

}
