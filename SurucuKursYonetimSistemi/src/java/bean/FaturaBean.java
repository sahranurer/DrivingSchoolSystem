package bean;

import dao.FaturaDao;
import entity.Fatura;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "FaturaBean")
@SessionScoped
public class FaturaBean implements Serializable{
     private FaturaDao faturaDao;
    private Fatura entity;
    
     private int page=1;
    private int pageSize=10;
    private int pageCount;

    public void next(){
        if (this.page == this.getPageCount()) {
            this.page = 1 ;
        }
        else
          this.page++;
    }
    public void previous(){
         if (this.page == 1) {
            this.page = this.getPageCount() ;
        }
         else
           this.page--;
    }
    
     public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getFaturaDao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
     public String create() {
        this.getFaturaDao().create(entity);
        this.entity = new Fatura();
        return "/fatura/list";
    }

    public List<Fatura> getRead() {
       return this.getFaturaDao().findAll(page, pageSize);
    }

    public String updatForm(Fatura e) {
        this.entity = e;
        return "/fatura/update";
    }

    public String update() {
        this.getFaturaDao().update(entity);
         this.entity = new Fatura();
        return "/fatura/list";
    }

    public void delete(Fatura c) {
        this.getFaturaDao().delete(c);

    }

    public FaturaDao getFaturaDao() {
        if (this.faturaDao == null) {
            this.faturaDao = new FaturaDao();
        }
        return faturaDao;
    }

    public void setFaturaDao(FaturaDao faturaDao) {
        this.faturaDao = faturaDao;
    }

    public Fatura getEntity() {
         if (this.entity == null) {
            this.entity = new Fatura();
        }
      
        return entity;
    }

    public void setEntity(Fatura entity) {
        this.entity = entity;
    }

}
