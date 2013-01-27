package com.vjlab.controller;

import javafx.scene.Node;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 21/01/13
 * Time: 21:37
 */
public interface Controller<DATA> {

    public Node getNode();

    public void configure(DATA data);

    public void unconfigure();

}
