package minhaturma.ufrpe.br.minhaturma.commons;

/**
 * Created by tuliodesouza
 */

public interface IRequest {

    void onStartRequest();
    void onCompleteRequest();

    void onRequestError(String message);

}
