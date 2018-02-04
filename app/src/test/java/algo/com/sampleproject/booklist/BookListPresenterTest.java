package algo.com.sampleproject.booklist;

import android.content.Context;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

import algo.com.sampleproject.model.Result;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by heinhtetaung on 4/2/18.
 */
public class BookListPresenterTest {

    @Test
    public void checkIfisEmptyListCorrect(){
        BookListContract.View mView = mock(BookListContract.View.class);
        BookListPresenter mPresenter = new BookListPresenter(mView);

        mPresenter.setResultList(new ArrayList<Result>());
        Assert.assertTrue(mPresenter.isListEmpty());
    }



}