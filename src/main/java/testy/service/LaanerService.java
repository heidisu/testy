package testy.service;

import testy.model.Laaner;
import testy.util.StoreUtil;

import java.util.List;

/**
 *
 * @author heidisu
 */
public class LaanerService {

    public List<Laaner> getLaanere() {
        return StoreUtil.getObjects(Laaner.class);
    }
}
