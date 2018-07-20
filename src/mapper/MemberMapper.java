package mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import model.Member;

public interface MemberMapper {

	@Delete("delete from member where email=#{value}")
	int delete(String id);

	@Insert("insert into member(email,password,name,regdate,tel,company) values(#{email},#{password},#{name},curdate(),#{tel},#{company})")
	int insert(Member mem);

	@Update("update member set password=#{password}, name=#{name}, email=#{email}, tel=#{tel}, company=#{company} where email=#{email}")
	int update(Member mem);
}
