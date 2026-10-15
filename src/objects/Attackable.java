package objects;

public interface Attackable {
	
	public boolean isAttackableByManel();
	
	public void attacked(int damage);
	
	public boolean attackYourself();
	
	public int getAttack();
	
}
