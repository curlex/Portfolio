
class Level
  attr_reader :level
  def initialize (max_level,min_level)
    @max_level = max_level
    @min_level = min_level
    @level = min_level
  end
  def advance_a_level
    @level +=1 if @level<@max_level
  end
  def drop_a_level
    @level-=1 if @level>@min_level
  end

end