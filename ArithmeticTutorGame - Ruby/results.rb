class Results
  include Enumerable
  attr_reader :result
  def initialize
    @result = Hash.new
  end
  def add (question,answer)
    @result[question.to_s] = answer.to_s

  end
  def sort
      @result.sort_by{|question,answer| answer}
  end
  def to_s
     sort.inject("Your results where as follows:"){|str,result| str+="\n"+result.join}
  end
end