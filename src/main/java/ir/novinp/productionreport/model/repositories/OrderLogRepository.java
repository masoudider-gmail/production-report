package ir.novinp.productionreport.model.repositories;

import ir.novinp.productionreport.model.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface OrderLogRepository<T extends OrderLog,ID> extends JpaRepository<T, ID> {
}
