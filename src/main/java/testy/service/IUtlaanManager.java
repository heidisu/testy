/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testy.service;

import java.util.List;
import java.util.Map;
import testy.model.Laaner;
import testy.model.Utlaan;

/**
 *
 * @author heidisu
 */
public interface IUtlaanManager {
  public Map<Laaner, List<Utlaan>> getForfallendeUtlaan();
}
