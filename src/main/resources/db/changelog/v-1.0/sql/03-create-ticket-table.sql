CREATE TABLE ticket (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        name varchar(50),
                        description varchar(100),
                        created_on DATE,
                        desired_resolution_date DATE,
                        assignee_id BIGINT,
                        owner_id BIGINT,
                        state_id ENUM('DRAFT', 'NEW', 'APPROVED', 'DECLINED', 'INPROGRESS', 'DONE', 'CANCELED') NOT NULL,
                        category_id BIGINT,
                        urgency_id ENUM('CRITICAL', 'HIGH', 'AVERAGE', 'LOW') NOT NULL,
                        approver_id BIGINT,
                        PRIMARY KEY (id),
                        FOREIGN KEY (assignee_id) REFERENCES user(id) ON DELETE SET NULL,
                        FOREIGN KEY (owner_id) REFERENCES user(id) ON DELETE SET NULL,
                        FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE SET NULL,
                        FOREIGN KEY (approver_id) REFERENCES user(id) ON DELETE SET NULL
);